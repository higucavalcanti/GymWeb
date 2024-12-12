import { FC } from "react";
import * as S from "./styles";
import { ArrowRight, Download, Heart, NotePencil } from "@phosphor-icons/react";
import { Rate } from "antd";
import moment from "moment";

interface CardProps {
  title?: string;
  content?: string;
  rateCard?: boolean;
  like?: boolean;
  extend?: boolean;
  details?: boolean;
  download?: boolean;
  edit?: boolean;
  editFunction?: () => void;
  onLikeToggle?: () => void;
  onDownload?: () => void;
  buttonFunction?: () => void;
  datePost?: Date;
  author?: string;
}

export const Card: FC<CardProps> = ({
  extend,
  title,
  content,
  rateCard,
  like,
  details,
  download,
  edit,
  editFunction,
  onLikeToggle,
  buttonFunction,
  datePost,
  onDownload,
  author,
}) => {
  return (
    <S.Container $extend={extend}>
      {like !== false && (
        <S.LikeArea onClick={onLikeToggle}>
          <Rate
            character={<Heart size={24} weight="fill" />}
            count={1}
            value={like ? 1 : 0}
            style={{ color: "#F21E51" }}
          />
        </S.LikeArea>
      )}
      {download && (
        <S.MaterialArea>
          {edit && (
            <div onClick={editFunction} style={{ cursor: "pointer" }}>
              <NotePencil size={32} weight="fill" />
            </div>
          )}
          <div onClick={onDownload} style={{ cursor: "pointer" }}>
            <Download size={32} weight="fill" />
          </div>
        </S.MaterialArea>
      )}

      <S.TitleArea>
        <section>
          <S.Title>{title}</S.Title>
          {datePost && <p>{moment(datePost).format("DD/MM/YYYY HH:mm")}</p>}
        </section>

        <S.RateArea>
          {rateCard && <Rate style={{ color: "#7fc7d9" }} />}
        </S.RateArea>
      </S.TitleArea>

      <S.Content>{content}</S.Content>
      <S.FooterArea>
        {author && <S.AuthorName>@:{author}</S.AuthorName>}
        {details && (
          <S.ButtonArea onClick={buttonFunction}>
            <label htmlFor="">Ver detalhes</label>
            <ArrowRight size={24} weight="bold" color="#7fc7d9" />
          </S.ButtonArea>
        )}
      </S.FooterArea>
    </S.Container>
  );
};